import React, { Component } from 'react';
import { Container, Row, Col, Table } from 'reactstrap';
import CalcPlainTable from './calc-plain-table';

import './calc-style.css';

class Average extends Component {
    constructor(props) {
        super(props);

        this.state = {
            loading: true,
            data: {
                "numero_de_filhos": 4,
                "salario": 2398.89
            }
        }
    }

    render() {
        return (
            <div className="calc-container average-container p-4">
                <h3>MÉDIA</h3>
                <hr/>
                <CalcPlainTable data={this.state.data} />
            </div>
        );
    }
}

export default Average;