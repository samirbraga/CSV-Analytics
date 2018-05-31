import React, { Component } from 'react';
import { Table } from 'reactstrap';
import Ionicon from 'react-ionicons';

import LoadingOverlay from '../util/loading-overlay';

import store from '../../store';

import './table-plot.css';

class TablePlot extends Component {
    constructor(props) {
        super(props);

        this.state = {
            loading: true,
            records: [],
            header: []
        }

        let csvData = store.getState().csv_data;
        if (csvData) {
            this.state = {
                records: csvData.records,
                header: csvData.header,
                loading: false
            };
        } else if (localStorage.getItem('csv_data')) {
            this.state = {
                ...JSON.parse(localStorage.getItem('csv_data')),
                loading: false
            };
        }
    }

    componentWillReceiveProps(newProps) {
        let csvData = store.getState().csv_data;
        this.setState({
            records: csvData.records,
            header: csvData.header,
            loading: false
        });
    }

    render() {
        return (
            <div className="data-table">
                <LoadingOverlay loading={this.state.loading} />
                <Table>
                    <thead>
                        <tr>
                            {this.state.header.map(title => (
                                <th key={title}>{title}</th>
                            ))}
                        </tr>
                    </thead>
                    <tbody>
                        {this.state.records.map(record => (
                            <tr>
                                {this.state.header.map(title => (
                                    <td key={record[title]} >
                                        {record[title]}
                                    </td>
                                ))}
                            </tr>
                        ))}
                    </tbody>
                </Table>
            </div>
        );
    }
}

export default TablePlot;