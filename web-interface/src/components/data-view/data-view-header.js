import React, { Component } from 'react';
import { Switch, Route, NavLink } from 'react-router-dom';
import Ionicon from 'react-ionicons';

import './data-view.css';

const DynamicTitle = props => (
    <Switch>
        {props.routes.map(calc => {
            const Comp = () => (
                <span>
                    &nbsp;
                    <Ionicon className="align-text-top" icon="ios-arrow-forward" color="#666666" fontSize="22px" />&nbsp;
                    <NavLink to={`/CSV-Analytics/data-visualization/${calc.key}`} >
                        {calc.name}
                    </NavLink>
                </span>
            );

            return (
                <Route
                    key={calc.key}
                    path={`/CSV-Analytics/data-visualization/${calc.key}`}
                    component={Comp}
                />
            );
        })}
    </Switch>
);

class DataViewHeader extends Component {
    uploadOtherFile() {
        localStorage.removeItem('csv_data');
        localStorage.removeItem('token');

        this.props.history.push('/CSV-Analytics');
    }

    render() {
        return (
            <div className="data-view-header px-3">
                <div className="float-left">
                    <h2 className="my-0">
                        <NavLink to="/CSV-Analytics/data-visualization" >
                            VISUALIZAÇÃO DOS DADOS
                            </NavLink>
                        <DynamicTitle routes={this.props.routes} />
                    </h2>
                </div>
                <div className="float-right">
                    <button onClick={this.uploadOtherFile.bind(this)} className={"btn"} >
                        ANALISAR OUTRO <strong>CSV</strong>
                    </button>
                </div>
            </div>
        );
    }
}

export default DataViewHeader;