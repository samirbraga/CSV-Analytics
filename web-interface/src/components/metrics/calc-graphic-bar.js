import React, { Component } from 'react';
import { Container, Row, Col, Table } from 'reactstrap';
import { Chart } from 'react-google-charts';
import Ionicon from 'react-ionicons';

import './calc-style.css';

class CalcGraphicBar extends Component {
    constructor(props) {
        super(props);
    }

    render() {
        function prepend(value, array) {
            var newArray = array.slice();
            newArray.unshift(value);
            return newArray;
        }

        let { data } = this.props;
        let { table } = this.props;

        data = data.slice(0, data.length - 1);
        data = prepend(
            [
                table,
                'absolute frequency'
            ],
            data.map(el => [el[0], parseFloat(el[1])])
        );

        return (
            <span {...this.props} className={'d-block ' + this.props.className}>
                <h3>{table}</h3>
                <hr />
                <Chart
                    chartType={'ColumnChart'}
                    data={data}
                    options={{
                        bars: 'horizontal'
                    }}
                    graph_id="ScatterChart"
                    width="100%"
                    height="400px"
                    legend_toggle
                />
            </span>
        );
    }
}

export default CalcGraphicBar;