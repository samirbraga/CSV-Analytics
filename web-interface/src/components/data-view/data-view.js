import React, { Component } from 'react';
import { withRouter } from 'react-router';
import { Switch, Route, NavLink } from 'react-router-dom';
import Ionicon from 'react-ionicons';

import store from '../../store';

import TablePlot from '../table-plot/table-plot';

import CalcOption from '../metrics/calc-option';

import './data-view.css';

let DynamicTitle = function (props) {
    return (
        <Switch>
            {props.routes.map(calc => {
                let Comp = () => (
                    <span>
                        &nbsp;<Ionicon className="align-text-top" icon="ios-arrow-forward" color="#666666" fontSize="22px" />&nbsp;
                        <NavLink to={"/CSV-Analytics/data-visualization/" + calc.key} >
                            {calc.name}
                        </NavLink>
                    </span>
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

        this.uploadOtherFile = this.uploadOtherFile.bind(this);
    }

    uploadOtherFile() {
        localStorage.removeItem('csv_data');
        localStorage.removeItem('token');

        this.props.history.push('/CSV-Analytics');
    }

    render() {
        return (
            <div className="data-view d-flex flex-column">
                <div className="data-view-header px-3">
                    <div className="float-left">
                        <h2 className="my-0">
                            <NavLink to="/CSV-Analytics/data-visualization" >
                                VISUALIZAÇÃO DOS DADOS
                            </NavLink>
                            <DynamicTitle routes={this.state.routes} />
                        </h2>
                    </div>
                    <div className="float-right">
                        <button onClick={this.uploadOtherFile} className={"btn"} >ANALISAR OUTRO <strong>CSV</strong></button>
                    </div>
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

export default withRouter(DataView);
