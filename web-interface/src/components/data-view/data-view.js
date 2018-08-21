import React, { Component } from 'react';
import { withRouter } from 'react-router';
import { Switch, Route } from 'react-router-dom';

import store from '../../store';

import TablePlot from '../table-plot/table-plot';
import CalcOption from '../metrics/calc-option';
import DataViewHeader from './data-view-header';

import './data-view.css';

class DataView extends Component {
    constructor(props) {
        super(props);

        let routes = [];
        let { calcOptions } = store.getState();
        for (let categ in calcOptions) routes.push(...calcOptions[categ]);

        this.state = {
            routes
        };
    }

    render() {
        return (
            <div className="data-view d-flex flex-column">
                <DataViewHeader {...this.props} routes={this.state.routes} />
                <Route render={({ location }) => (
                    <Switch>
                        <Route path={"/CSV-Analytics/data-visualization/"} exact component={TablePlot} />
                        <Route
                            exact
                            path={"/CSV-Analytics/data-visualization/:key"}
                            render={(props) => (
                                <CalcOption calcKey={props.match.params.key} />
                            )}
                        />
                    </Switch>
                )} />
            </div>
        );
    }
}

export default withRouter(DataView);
