import React, { Component } from 'react';
import { Container, Row, Col, Table } from 'reactstrap';
import { Chart } from 'react-google-charts';
import Ionicon from 'react-ionicons';

import './calc-style.css';

class CalcBoxplot extends Component {
    constructor(props) {
        super(props);
    }

    render() {
        let { data } = this.props;
        let { table } = this.props;

        if (data && data.length) {
            var dataTable = new window.google.visualization.DataTable();
            dataTable.addColumn('string', table);
    
            for (let i = 0; i < data.length - 5; i++) {
                dataTable.addColumn('number', 'series'+i);
            }
    
            dataTable.addColumn({ id: 'max', type: 'number', role: 'interval' });
            dataTable.addColumn({ id: 'min', type: 'number', role: 'interval' });
            dataTable.addColumn({ id: 'firstQuartile', type: 'number', role: 'interval' });
            dataTable.addColumn({ id: 'median', type: 'number', role: 'interval' });
            dataTable.addColumn({ id: 'thirdQuartile', type: 'number', role: 'interval' });
    
            let row = [table, ...data];
    
            dataTable.addRows([row]);
            return (
                <span {...this.props} className={'d-block ' + this.props.className}>
                    <h3>{table}</h3>
                    <hr />
                    <Chart
                        chartType={'LineChart'}
                        data={dataTable}
                        options={{
                            title: table,
                            legend: { position: 'none' },
                            hAxis: {
                                gridlines: { color: '#fff' }
                            },
                            lineWidth: 0,
                            series: [{ 'color': '#D3362D' }],
                            intervals: {
                                barWidth: 1,
                                boxWidth: 1,
                                lineWidth: 2,
                                style: 'boxes'
                            },
                            interval: {
                                max: {
                                    style: 'bars',
                                    fillOpacity: 1,
                                    color: '#777'
                                },
                                min: {
                                    style: 'bars',
                                    fillOpacity: 1,
                                    color: '#777'
                                }
                            }
                        }}
                        graph_id={table + Math.floor(Math.random() * 100)}
                        width="100%"
                        height="400px"
                    />
                </span>
            );
        } else {
            return <span></span>
        }

    }
}

export default CalcBoxplot;