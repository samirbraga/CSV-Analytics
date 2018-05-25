import React, { Component } from 'react';
import { Container, Row, Col } from 'reactstrap';

import './App.css';

import Sidebar from './components/sidebar/sidebar.js';
import DropArea from './components/drop-area/drop-area.js';

class App extends Component {
  render() {
    return (
      <Container fluid className="App">
        <Row className="h-100">
          <Col className="h-100 px-0" md={3} >
            <Sidebar />
          </Col>
          <Col className="h-100 px-0" md={9} >
            <DropArea />
          </Col>
        </Row>
      </Container>
    );
  }
}

export default App;
