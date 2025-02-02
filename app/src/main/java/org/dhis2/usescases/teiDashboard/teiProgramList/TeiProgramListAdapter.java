package org.dhis2.usescases.teiDashboard.teiProgramList;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.compose.ui.platform.ComposeView;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import org.dhis2.R;
import org.dhis2.usescases.main.program.ProgramUiModel;

import java.util.ArrayList;
import java.util.List;

public class TeiProgramListAdapter extends RecyclerView.Adapter<TeiProgramListEnrollmentViewHolder> {

    private TeiProgramListContract.Presenter presenter;
    private List<TeiProgramListItem> listItems;
    private List<EnrollmentViewModel> activeEnrollments;
    private List<EnrollmentViewModel> inactiveEnrollments;
    private List<ProgramUiModel> programs;
    private List<ProgramUiModel> possibleEnrollmentPrograms;

    TeiProgramListAdapter(TeiProgramListContract.Presenter presenter) {
        this.presenter = presenter;
        this.listItems = new ArrayList<>();
        this.activeEnrollments = new ArrayList<>();
        this.inactiveEnrollments = new ArrayList<>();
        this.possibleEnrollmentPrograms = new ArrayList<>();
        this.programs = new ArrayList<>();
    }

    @NonNull
    @Override
    public TeiProgramListEnrollmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ViewDataBinding binding;
        ComposeView composeView = null;

        switch (viewType) {
            case TeiProgramListItem.TeiProgramListItemViewType.ALL_PROGRAMS_DASHBOARD:
                binding = DataBindingUtil.inflate(inflater, R.layout.item_tei_all_programs_dashboard_title, parent, false);
                break;
            case TeiProgramListItem.TeiProgramListItemViewType.FIRST_TITLE:
                binding = DataBindingUtil.inflate(inflater, R.layout.item_tei_programs_active_title, parent, false);
                break;
            case TeiProgramListItem.TeiProgramListItemViewType.ACTIVE_ENROLLMENT:
                binding = DataBindingUtil.inflate(inflater, R.layout.item_tei_programs_enrollment, parent, false);
                break;
            case TeiProgramListItem.TeiProgramListItemViewType.PROGRAM:
                binding = DataBindingUtil.inflate(inflater, R.layout.item_tei_programs_programs, parent, false);
                break;
            case TeiProgramListItem.TeiProgramListItemViewType.SECOND_TITLE:
                binding = DataBindingUtil.inflate(inflater, R.layout.item_tei_programs_inactive_title, parent, false);
                break;
            case TeiProgramListItem.TeiProgramListItemViewType.INACTIVE_ENROLLMENT:
                binding = DataBindingUtil.inflate(inflater, R.layout.item_tei_programs_enrollment_inactive, parent, false);
                break;
            case TeiProgramListItem.TeiProgramListItemViewType.THIRD_TITLE:
                binding = DataBindingUtil.inflate(inflater, R.layout.item_tei_programs_to_enroll_title, parent, false);
                break;
            case TeiProgramListItem.TeiProgramListItemViewType.PROGRAMS_TO_ENROLL:
                binding = DataBindingUtil.inflate(inflater, R.layout.item_tei_programs_programs, parent, false);
                composeView = new ComposeView(parent.getContext());
                break;
            default:
                binding = DataBindingUtil.inflate(inflater, R.layout.item_tei_programs_enrollment, parent, false);
                break;
        }

        return new TeiProgramListEnrollmentViewHolder(binding, composeView);
    }

    @Override
    public int getItemViewType(int position) {
        return listItems.get(position).getViewType();
    }

    @Override
    public void onBindViewHolder(@NonNull TeiProgramListEnrollmentViewHolder holder, int position) {
        switch (listItems.get(position).getViewType()) {
            case TeiProgramListItem.TeiProgramListItemViewType.ALL_PROGRAMS_DASHBOARD:
                holder.bind(presenter, null, null);
                break;
            case TeiProgramListItem.TeiProgramListItemViewType.FIRST_TITLE:
                holder.bind(presenter, null, null);
                break;
            case TeiProgramListItem.TeiProgramListItemViewType.ACTIVE_ENROLLMENT:
                holder.bind(presenter, listItems.get(position).getEnrollmentModel(), listItems.get(position).getProgramModel());
                break;
            case TeiProgramListItem.TeiProgramListItemViewType.PROGRAM:
                holder.bind(presenter, null, listItems.get(position).getProgramModel());
                break;
            case TeiProgramListItem.TeiProgramListItemViewType.SECOND_TITLE:
                holder.bind(presenter, null, null);
                break;
            case TeiProgramListItem.TeiProgramListItemViewType.INACTIVE_ENROLLMENT:
                holder.bind(presenter, listItems.get(position).getEnrollmentModel(), null);
                break;
            case TeiProgramListItem.TeiProgramListItemViewType.THIRD_TITLE:
                holder.bind(presenter, null, null);
                break;
            case TeiProgramListItem.TeiProgramListItemViewType.PROGRAMS_TO_ENROLL:
                holder.bind(presenter, null, listItems.get(position).getProgramModel());
                break;
            default:
                break;
        }
    }

    @Override
    public int getItemCount() {
        return listItems != null ? listItems.size() : 0;
    }

    void setActiveEnrollments(List<EnrollmentViewModel> enrollments) {
        this.activeEnrollments.clear();
        this.activeEnrollments.addAll(enrollments);
        orderList();
    }

    void setOtherEnrollments(List<EnrollmentViewModel> enrollments) {
        this.inactiveEnrollments.clear();
        this.inactiveEnrollments.addAll(enrollments);
        orderList();
    }

    void setPrograms(List<ProgramUiModel> programs) {
        this.programs.clear();
        this.programs.addAll(programs);
        orderList();
    }

    private void orderList() {
        possibleEnrollmentPrograms.clear();
        listItems.clear();

        TeiProgramListItem allProgramsDashBoardItem = new TeiProgramListItem(null, null, TeiProgramListItem.TeiProgramListItemViewType.ALL_PROGRAMS_DASHBOARD);
        if (!activeEnrollments.isEmpty()) {
            listItems.add(allProgramsDashBoardItem);
        }

        TeiProgramListItem firstTeiProgramListItem = new TeiProgramListItem(null, null, TeiProgramListItem.TeiProgramListItemViewType.FIRST_TITLE);
        listItems.add(firstTeiProgramListItem);

        for (EnrollmentViewModel enrollmentModel : activeEnrollments) {
            TeiProgramListItem teiProgramListItem = new TeiProgramListItem(enrollmentModel, null, TeiProgramListItem.TeiProgramListItemViewType.ACTIVE_ENROLLMENT);
            listItems.add(teiProgramListItem);
        }

        if (!inactiveEnrollments.isEmpty()) {
            TeiProgramListItem secondTeiProgramListItem = new TeiProgramListItem(null, null, TeiProgramListItem.TeiProgramListItemViewType.SECOND_TITLE);
            listItems.add(secondTeiProgramListItem);

            for (EnrollmentViewModel enrollmentModel : inactiveEnrollments) {
                TeiProgramListItem teiProgramListItem = new TeiProgramListItem(enrollmentModel, null, TeiProgramListItem.TeiProgramListItemViewType.INACTIVE_ENROLLMENT);
                listItems.add(teiProgramListItem);
            }
        }

        boolean found;
        boolean active;
        for (ProgramUiModel programModel : programs) {
            found = false;
            active = false;
            for (EnrollmentViewModel enrollment : activeEnrollments) {
                if (programModel.getTitle().equals(enrollment.programName())) {
                    found = true;
                    active = true;
                }
            }

            if (!found)
                for (EnrollmentViewModel enrollment : inactiveEnrollments) {
                    if (programModel.getTitle().equals(enrollment.programName())) {
                        found = true;
                        active = false;
                    }
                }

            if (found) {
                if (!active && !programModel.getOnlyEnrollOnce())
                    possibleEnrollmentPrograms.add(programModel);
            } else
                possibleEnrollmentPrograms.add(programModel);
        }

        if (!possibleEnrollmentPrograms.isEmpty()) {
            TeiProgramListItem thirdTeiProgramListItem = new TeiProgramListItem(null, null, TeiProgramListItem.TeiProgramListItemViewType.THIRD_TITLE);
            listItems.add(thirdTeiProgramListItem);

            for (ProgramUiModel programToEnroll : possibleEnrollmentPrograms) {
                TeiProgramListItem teiProgramListItem = new TeiProgramListItem(null, programToEnroll, TeiProgramListItem.TeiProgramListItemViewType.PROGRAMS_TO_ENROLL);
                listItems.add(teiProgramListItem);
            }
        }

        notifyDataSetChanged();
    }
}
