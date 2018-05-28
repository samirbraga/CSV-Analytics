import React, { Component } from 'react';
import (__dirname + '/sidebar.css');

class Sidebar extends Component {
    render() {
        return (
            <div className="sidebar w-100 h-100 d-flex flex-column justify-content-between align-items-start">
                <div className="sidebar-header text-align-left d-flex flex-column">
                    <img className="sidebar-header-img mr-auto" width={80} src="/CSV-Analytics/imgs/icon.png" alt="Logo - CSV Analytics"/>
                    <span className="h3 sidebar-header-title d-block mt-2">
                        <span className="highlight" >CSV</span> ANALYTICS
                    </span>
                </div>
                <div className="sidebar-body">
                    <h1>ANALISE SEUS DADOS AQUI, AGORA E GRATUITAMENTE.</h1>
                    <p className="mt-3 d-block">Arraste seu arquivo csv ao lado ou clique para procurá-lo</p>
                </div>
                <div className="sidebar-footer"></div>
            </div>
        );
    }
}

export default Sidebar;