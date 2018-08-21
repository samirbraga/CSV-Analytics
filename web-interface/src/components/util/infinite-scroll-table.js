import React, { Component } from 'react';
import { Table } from 'reactstrap';
import cx from 'classnames';

import './infinite-scroll-table.css';

class InfiniteScrollTable extends Component {
    constructor(props) {
        super(props);

        this.state = {
            loading: true,
            records: [],
            header: [],
            loadedRows: 50
        }

        this.loadedOnEndScroll = this.loadedOnEndScroll.bind(this);
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
        let records = [];
        let footer = [];

        if (this.props.hasFooter) {
            records = this.props.records.slice(0, this.props.records.length-1);
            footer = this.props.records[this.props.records.length-1];
        } else {
            records = this.props.records;
        }

        return (
            <div
                onScroll={this.loadedOnEndScroll} 
                {...{ ...this.props, data: null, records: null, header: null }}
                className={cx('scrollable', this.props.className)}>
                <Table>
                    <thead>
                        <tr>
                            {this.props.header.map(title => (
                                <th key={title}>{title}</th>
                            ))}
                        </tr>
                    </thead>
                    <tbody>
                        {records.slice(0, this.state.loadedRows).map(row => (
                            <tr>
                                {row.map(value => (
                                    <td>{value}</td>
                                ))}
                            </tr>
                        ))}
                    </tbody>
                    {
                        this.props.hasFooter &&
                        <tfoot>
                            <tr>
                                {footer.map(val => (
                                    <td>{val}</td>
                                ))}
                            </tr>
                        </tfoot>
                    }
                </Table>
            </div>
        );
    }
}

export default InfiniteScrollTable;