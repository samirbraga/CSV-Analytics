import React, { Component } from 'react';

import './calc-style.css';
import InfiniteScrollTable from '../util/infinite-scroll-table';

class CalcQualitativeFrequencyTable extends Component {
    render() {
        let { data } = this.props;
        let { table } = this.props;

        return (
            <span {...this.props} className={'d-block ' + this.props.className}>
                <h3>{table}</h3>
                <hr />
                <InfiniteScrollTable
                    header={[
                        'Classe',
                        'Freq. Absoluta',
                        'Freq. Relativa'
                    ]}
                    records={data}
                    hasFooter={true}
                    className="calc-plain-table"
                />
            </span>
        );
    }
}

export default CalcQualitativeFrequencyTable ;