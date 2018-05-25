import React, { Component } from 'react';
import (__dirname + '/sidebar.css');

class Sidebar extends Component {
    render() {
        return (
            <div className="sidebar w-100 h-100 d-flex flex-column justify-content-center">
                <h1>ANALISE SEUS DADOS AQUI, AGORA E GRATUITAMENTE.</h1>
                <p>ARRASTE SEU ARQUIVO CSV AO LADO</p>
            </div>
        );
    }
}

export default Sidebar;