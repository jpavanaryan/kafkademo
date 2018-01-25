import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { EmployeeProject } from './employee-project.model';
import { ResponseWrapper, createRequestOption } from '../../shared';

@Injectable()
export class EmployeeProjectService {

    private resourceUrl =  SERVER_API_URL + '/projects/api/employee-projects';

    constructor(private http: Http) { }

    create(employeeProject: EmployeeProject): Observable<EmployeeProject> {
        const copy = this.convert(employeeProject);
        return this.http.post(this.resourceUrl, copy).map((res: Response) => {
            const jsonResponse = res.json();
            return this.convertItemFromServer(jsonResponse);
        });
    }

    update(employeeProject: EmployeeProject): Observable<EmployeeProject> {
        const copy = this.convert(employeeProject);
        return this.http.put(this.resourceUrl, copy).map((res: Response) => {
            const jsonResponse = res.json();
            return this.convertItemFromServer(jsonResponse);
        });
    }

    find(id: number): Observable<EmployeeProject> {
        return this.http.get(`${this.resourceUrl}/${id}`).map((res: Response) => {
            const jsonResponse = res.json();
            return this.convertItemFromServer(jsonResponse);
        });
    }

    query(req?: any): Observable<ResponseWrapper> {
        const options = createRequestOption(req);
        return this.http.get(this.resourceUrl, options)
            .map((res: Response) => this.convertResponse(res));
    }

    delete(id: number): Observable<Response> {
        return this.http.delete(`${this.resourceUrl}/${id}`);
    }

    private convertResponse(res: Response): ResponseWrapper {
        const jsonResponse = res.json();
        const result = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            result.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return new ResponseWrapper(res.headers, result, res.status);
    }

    /**
     * Convert a returned JSON object to EmployeeProject.
     */
    private convertItemFromServer(json: any): EmployeeProject {
        const entity: EmployeeProject = Object.assign(new EmployeeProject(), json);
        return entity;
    }

    /**
     * Convert a EmployeeProject to a JSON which can be sent to the server.
     */
    private convert(employeeProject: EmployeeProject): EmployeeProject {
        const copy: EmployeeProject = Object.assign({}, employeeProject);
        return copy;
    }
}
