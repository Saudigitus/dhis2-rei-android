package com.dhis2.usescases.teiDashboard.teiDataDetail;

import android.util.Log;

import com.dhis2.data.metadata.MetadataRepository;
import com.dhis2.usescases.teiDashboard.DashboardProgramModel;
import com.dhis2.usescases.teiDashboard.DashboardRepository;

import org.hisp.dhis.android.core.enrollment.EnrollmentStatus;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

/**
 * Created by frodriguez on 12/13/2017.
 */

public class TeiDataDetailPresenter implements TeiDataDetailContracts.Presenter {

    private final DashboardRepository dashboardRepository;
    private final MetadataRepository metadataRepository;
    private final CompositeDisposable disposable;
    private final EnrollmentStatusStore enrollmentStore;
    private TeiDataDetailContracts.View view;

    TeiDataDetailPresenter(DashboardRepository dashboardRepository, MetadataRepository metadataRepository, EnrollmentStatusStore enrollmentStatusStore) {
        this.dashboardRepository = dashboardRepository;
        this.metadataRepository = metadataRepository;
        this.enrollmentStore = enrollmentStatusStore;
        disposable = new CompositeDisposable();
    }

    @Override
    public void init(TeiDataDetailContracts.View view, String uid, String programUid, String enrollmentUid) {
        this.view = view;

        if (programUid != null) {
            disposable.add(Observable.zip(
                    metadataRepository.getTrackedEntityInstance(uid),
                    dashboardRepository.getEnrollment(programUid, uid),
                    dashboardRepository.getProgramStages(programUid),
                    dashboardRepository.getTEIEnrollmentEvents(programUid, uid),
                    metadataRepository.getProgramTrackedEntityAttributes(programUid),
                    dashboardRepository.getTEIAttributeValues(programUid, uid),
                    metadataRepository.getTeiOrgUnit(uid),
                    metadataRepository.getTeiActivePrograms(uid),
                    dashboardRepository.getRelationships(programUid, uid),
                    DashboardProgramModel::new)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                            view::setData,
                            throwable -> Log.d("ERROR", throwable.getMessage()))
            );

            disposable.add(
                    enrollmentStore.enrollmentStatus(enrollmentUid)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(
                                    view.handleStatus(),
                                    throwable -> Log.d("ERROR", throwable.getMessage()))

            );
        } else {
            //TODO: NO SE HA SELECCIONADO PROGRAMA
            disposable.add(Observable.zip(
                    metadataRepository.getTrackedEntityInstance(uid),
                    metadataRepository.getProgramTrackedEntityAttributes(null),
                    dashboardRepository.getTEIAttributeValues(null, uid),
                    metadataRepository.getTeiOrgUnit(uid),
                    metadataRepository.getTeiActivePrograms(uid),
                    DashboardProgramModel::new)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(view::setData,
                            throwable -> Log.d("ERROR", throwable.getMessage()))
            );
        }
    }

    @Override
    public void onBackPressed() {
        view.getAbstracContext().onBackPressed();
    }

    @Override
    public void editData() {
        view.setDataEditable();
    }

    @Override
    public void onButtonActionClick(DashboardProgramModel dashboardProgramModel) {
        if (dashboardProgramModel.getCurrentProgram().accessDataWrite()) {
            Flowable<Long> flowable = null;
            EnrollmentStatus newStatus;
            switch (dashboardProgramModel.getCurrentEnrollment().enrollmentStatus()) {
                case ACTIVE:
                    newStatus = EnrollmentStatus.COMPLETED;
                    break;
                case COMPLETED:
                    newStatus = EnrollmentStatus.ACTIVE;
                    break;
                case CANCELLED:
                    newStatus = EnrollmentStatus.ACTIVE;
                    break;
                default:
                    newStatus = EnrollmentStatus.ACTIVE;
                    break;
            }
            flowable = enrollmentStore.save(dashboardProgramModel.getCurrentEnrollment().uid(), newStatus);
            disposable.add(flowable
                    .subscribeOn(Schedulers.computation())
                    .observeOn(AndroidSchedulers.mainThread())
                    .map(result -> newStatus)
                    .subscribe(
                            view.handleStatus(),
                            Timber::d)
            );
        } else
            view.displayMessage(null);
    }

    @Override
    public void onDeactivate(DashboardProgramModel dashboardProgramModel) {
        if (dashboardProgramModel.getCurrentProgram().accessDataWrite())
            disposable.add(enrollmentStore.save(dashboardProgramModel.getCurrentEnrollment().uid(), EnrollmentStatus.CANCELLED)
                    .subscribeOn(Schedulers.computation())
                    .observeOn(AndroidSchedulers.mainThread())
                    .map(result->EnrollmentStatus.CANCELLED)
                    .subscribe(
                            view.handleStatus(),
                            Timber::d)
            );
        else
            view.displayMessage(null);

    }

}
