import React, { Component } from 'react';
import { Container, Row, Col, Table } from 'reactstrap';
import Ionicon from 'react-ionicons';

import './calc-style.css';

class CalcPlainTable extends Component {
    constructor(props) {
        super(props);
    }

    render() {
        let { data } = this.props;
        let dataKeys = Object.keys(this.props.data).map(k => k.toString());

        return (
            <Container fluid className={"mt-4" + this.props.className}>
                <Row>
                    <Col md={{ size: 6, offset: 3 }} style={{
                        background: "rgba(255, 255, 255, 0.5)",
                        boxShadow: '0 0.5rem 2rem 0 rgba(0, 0, 0, .1)',
                        borderRadius: '0.3rem'
                    }}>
                        <Table className="calc-plain-table my-2" >
                            <tbody>
                                {
                                    dataKeys.map((field, i) => (
                                        <tr>
                                            <th>{field.toString()}</th>
                                            <td>{data[field]}</td>
                                        </tr>
                                    ))
                                }
                                <tr></tr>
                            </tbody>
                        </Table>
                    </Col>
                </Row>
            </Container>
        );
    }
}

export default CalcPlainTable;