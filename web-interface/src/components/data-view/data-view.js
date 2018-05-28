import React, { Component } from 'react';
import { Switch, Route, NavLink } from 'react-router-dom';

import calcOptions from '../../data';

import TablePlot from '../table-plot/table-plot';

import(__dirname + '/data-view.css');

let routes = [];

for (let categ in calcOptions) routes.push(...calcOptions[categ]);

let DynamicTitle = () => (
    <Switch>
        {routes.map(calc => {
            let Comp = () => (
                <NavLink to={"/CSV-Analytics/data-visualization/" + calc.key} >
                    {calc.name}
                </NavLink>
            )
            return (
                <Route path={"/CSV-Analytics/data-visualization/" + calc.key} component={Comp}></Route>
            )
        })}
    </Switch>
);

class DataView extends Component {
    render() {
        return (
            <div class="data-view d-flex flex-column">
                <div className="data-view-header py-4 px-3">
                    <h2>
                        <NavLink to="/CSV-Analytics/data-visualization" >
                            VISUALIZAÇÃO DOS DADOS
                        </NavLink>
                        &nbsp;>&nbsp;
                        <DynamicTitle />
                    </h2>
                </div>
                <Route render={({ location }) => (
                    <Switch>
                        <Route path={"/CSV-Analytics/data-visualization/"} component={TablePlot} />
                    </Switch>
                )} />
            </div>
        );
    }
}

export default DataView;