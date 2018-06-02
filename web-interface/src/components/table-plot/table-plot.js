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
            header: [],
            loadedRows: 50
        }

        let csvData = store.getState().csv_data;
        if (csvData) {
            this.state = {
                ...this.state,
                records: csvData.records,
                header: csvData.header,
                loading: false
            };
        } else if (localStorage.getItem('csv_data')) {
            this.state = {
                ...this.state,
                ...JSON.parse(localStorage.getItem('csv_data')),
                loading: false
            };
        }

        this.dataTable = React.createRef();

        this.loadedOnEndScroll = this.loadedOnEndScroll.bind(this);
    }

    componentWillReceiveProps(newProps) {
        let csvData = store.getState().csv_data;
        this.setState({
            records: csvData.records,
            header: csvData.header,
            loading: false
        });
    }

    loadedOnEndScroll(e) {
        let dataTable = e.target;
        let oldLoadedRows = this.state.loadedRows;

        if (dataTable.scrollHeight - dataTable.scrollTop === dataTable.clientHeight) {
            this.setState({
                loadedRows: oldLoadedRows + 50
            });
        }
    }

    render() {
        return (
            <div ref={this.dataTable} onScroll={this.loadedOnEndScroll} className="data-table">
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
                        {this.state.records.slice(0, this.state.loadedRows).map((record, i) => (
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