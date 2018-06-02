import React, { Component } from 'react';
import { Container, Row, Col, Table } from 'reactstrap';
import Ionicon from 'react-ionicons';

import './calc-style.css';
import InfiniteScrollTable from '../util/infinite-scroll-table';

class CalcQuantitativeFrequencyTable extends Component {
    constructor(props) {
        super(props);
    }

    render() {
        let { data } = this.props;
        let { table } = this.props;

        return (
            <span {...{ ...this.props, data: null, records: null, header: null }} className={'d-block ' + this.props.className}>
                <h3>{table}</h3>
                <hr/>
                <InfiniteScrollTable
                    header={[
                        'Classe',
                        'Freq. Absoluta',
                        'Freq. Relativa',
                        'Freq. Absol. Acumulada',
                        'Freq. Relat. Acumulada'
                    ]}
                    records={data}
                    hasFooter={true}
                    className="calc-plain-table"
                />
            </span>
        );
    }
}

export default CalcQuantitativeFrequencyTable ;