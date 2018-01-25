import { Component, OnInit, OnDestroy } from '@angular/core';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { EmployeeProject } from './employee-project.model';
import { EmployeeProjectService } from './employee-project.service';
import { Principal, ResponseWrapper } from '../../shared';

@Component({
    selector: 'jhi-employee-project',
    templateUrl: './employee-project.component.html'
})
export class EmployeeProjectComponent implements OnInit, OnDestroy {
employeeProjects: EmployeeProject[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private employeeProjectService: EmployeeProjectService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {
    }

    loadAll() {
        this.employeeProjectService.query().subscribe(
            (res: ResponseWrapper) => {
                this.employeeProjects = res.json;
            },
            (res: ResponseWrapper) => this.onError(res.json)
        );
    }
    ngOnInit() {
        this.loadAll();
        this.principal.identity().then((account) => {
            this.currentAccount = account;
        });
        this.registerChangeInEmployeeProjects();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: EmployeeProject) {
        return item.id;
    }
    registerChangeInEmployeeProjects() {
        this.eventSubscriber = this.eventManager.subscribe('employeeProjectListModification', (response) => this.loadAll());
    }

    private onError(error) {
        this.jhiAlertService.error(error.message, null, null);
    }
}
