import React, { Component } from 'react';
import { withRouter } from 'react-router';
import { Container, Row, Col } from 'reactstrap';

import Toolbar from '../toolbar/toolbar';
import DataView from '../data-view/data-view';

class PanelPage extends Component {
    componentWillMount() {
        if (!localStorage.getItem('token') || !localStorage.getItem('csv_data')) {
            this.props.history.push('/CSV-Analytics');
        }
    }

    render() {
        return (
            <Container fluid className="h-100">
                <Row className="h-100">
                    <Col md={4} className="p-0 h-100" >
                        <Toolbar />
                    </Col>
                    <Col md={8} className="p-0 h-100" >
                        <DataView />
                    </Col>
                </Row>
            </Container>
        );
    }
}

export default withRouter(PanelPage);