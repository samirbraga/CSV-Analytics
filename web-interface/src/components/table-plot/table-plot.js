import React, { Component } from 'react';
import { Table } from 'reactstrap';
import Ionicon from 'react-ionicons';

import LoadingOverlay from '../util/loading-overlay';

import './table-plot.css';

class TablePlot extends Component {
    constructor(props) {
        super(props);

        this.state = {
            loading: true,
            records: [],
            header: []
        }
    }

    componentDidMount() {
        var myInit = {
            method: 'GET',
            mode: 'cors',
            cache: 'default'
        };

        fetch('https://next.json-generator.com/api/json/get/Vy3jwfrkB', myInit)
        .then(response => {
            response.json().then(json => {
                this.setState({
                    records: json,
                    header: Object.keys(json[0]).slice(1)
                }, () => {
                    setTimeout(() => {
                        this.setState({
                            loading: false
                        });
                    }, 500)
                });
            });
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
                                <th>{title}</th>
                            ))}
                        </tr>
                    </thead>
                    <tbody>
                        {this.state.records.map(record => (
                            <tr>
                                {this.state.header.map(title => (
                                    <td>
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