import React, { Component } from 'react';
import { Chart } from 'react-google-charts';

import './calc-style.css';

class CalcHistogram extends Component {
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
                'relative frequency'
            ],
            data.map(el => [el[0], parseFloat(el[2])])
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
                    graph_id={table + Math.floor(Math.random() * 100)}
                    width="100%"
                    height="400px"
                    legend_toggle
                />
            </span>
        );
    }
}

export default CalcHistogram;