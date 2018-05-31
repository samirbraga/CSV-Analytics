import React, { Component } from 'react';
import { Switch, Route, NavLink } from 'react-router-dom';

import store from '../../store';

import TablePlot from '../table-plot/table-plot';

import CalcOption from '../metrics/calc-option';

import './data-view.css';

let DynamicTitle = function (props) {
    return (
        <Switch>
            {props.routes.map(calc => {
                let Comp = () => (
                    <NavLink to={"/CSV-Analytics/data-visualization/" + calc.key} >
                        {calc.name}
                    </NavLink>
                )
                return (
                    <Route key={calc.key} path={"/CSV-Analytics/data-visualization/" + calc.key} component={Comp}></Route>
                )
            })}
        </Switch>
    );
};

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
                <div className="data-view-header py-4 px-3">
                    <h2>
                        <NavLink to="/CSV-Analytics/data-visualization" >
                            VISUALIZAÇÃO DOS DADOS
                        </NavLink>
                        &nbsp;>&nbsp;
                        <DynamicTitle routes={this.state.routes} />
                    </h2>
                </div>
                <Route render={({ location }) => (
                    <Switch>
                        <Route path={"/CSV-Analytics/data-visualization/"} exact component={TablePlot} />
                        <Route exact path={"/CSV-Analytics/data-visualization/:key"} render={(props) => {
                            return <CalcOption calcKey={props.match.params.key} />;
                        }} />
                    </Switch>
                )} />
            </div>
        );
    }
}

export default DataView;