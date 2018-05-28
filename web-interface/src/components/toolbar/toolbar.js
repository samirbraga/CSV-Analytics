import React, { Component } from 'react';
import { Link } from 'react-router-dom';
import { Container, Row, Col } from 'reactstrap';
import Ionicon from 'react-ionicons';

import calcOptions from '../../data';

import SlinButton from '../util/slin-button';
import RoundButton from '../util/round-button';

import './toolbar.css';

let translater = {
    "metrics": "MÉTRICAS",
    "graphic-qualitatives": "GRÁFICAS QUALITATIVAS",
    "graphic-quantitatives": "GRÁFICAS QUANTITATIVAS"
};

class Toolbar extends Component {
    constructor(props) {
        super(props);

        this.state = {
            filteredCategories: calcOptions,
            searching: false
        };

        this.searchInput = React.createRef();
        this.toolbarHeader = React.createRef();
        this.toolbarContent = React.createRef();

        this.searchCalc = this.searchCalc.bind(this);
        this.toggleSearch = this.toggleSearch.bind(this);
    }

    componentDidMount() {
        let headerEl = this.toolbarHeader.current;
        let contentEl = this.toolbarContent.current;
        contentEl.addEventListener('scroll', function(e) {
            if (this.scrollTop > 20 && !headerEl.classList.contains('shadow')) {
                headerEl.classList.add('shadow');
            } else {
                if (this.scrollTop <= 20 && headerEl.classList.contains('shadow')) {
                    headerEl.classList.remove('shadow');
                }
            }
        });
    }

    toggleSearch() {
        this.setState((prev) => {
            if (prev.searching) {
                this.searchCalc();
            }

            return {
                ...prev,
                searching: !prev.searching
            }
        }, () => {
            if (this.state.searching) {
                this.searchInput.current.focus();
            }
        });
    }

    searchCalc(searched='') {
        function lint(str) {
            return str.trim()
                .replace(/[áãâàä]/gui, 'a')
                .replace(/[èéëê]/gui, 'e')
                .replace(/[óòõôö]/gui, 'o')
                .replace(/[úùûü]/gui, 'u')
                .replace(/[íîïì]/gui, 'i');
        }

        searched = lint(searched);

        let filteredCategories = {};
        let calcCategories = Object.keys(calcOptions);
        calcCategories.forEach(cat => {
            let selfSearchedItems = [];
            selfSearchedItems = calcOptions[cat].filter(opt => {
                return (new RegExp(searched, 'igu').test(lint(opt.name)) || new RegExp(searched, 'igu').test(lint(opt.key)));
            });

            if (selfSearchedItems.length) {
                filteredCategories[cat] = selfSearchedItems;
            }
        });

        this.setState({
            filteredCategories
        });
    }

    render() {
        let icons = ['ios-pie-outline', 'ios-pulse', 'ios-analytics-outline'];
        let filteredCategoriesKeys = Object.keys(this.state.filteredCategories);

        return (
            <div className="toolbar h-100 d-flex flex-column">
                <div ref={this.toolbarHeader} className="toolbar-header pb-3 pt-3 px-3 clearfix">
                    <div className={"search-container float-left " + (this.state.searching ? '' : 'd-none')}>
                        <input ref={this.searchInput} type="text" onKeyUp={(e) => this.searchCalc(e.target.value)} />
                    </div>
                    <div className={"toolbar-header-title float-left mt-1 " + (this.state.searching ? 'd-none' : '')} >
                        <img className="d-inline-block align-middle mr-3" src="/CSV-Analytics/imgs/white-icon.png" alt="Logo - CSV-Analytics"/>
                        <span className="d-inline-block align-middle" ><strong>CSV</strong> ANALYTICS</span>
                    </div>
                    <RoundButton onClick={this.toggleSearch} icon="ios-search"
                        actived={this.state.searching}
                        className="float-right" 
                    />
                </div>
                <div ref={this.toolbarContent} className="toolbar-content pt-0">
                    <hr className="m-0" />
                    {filteredCategoriesKeys.length ? 
                        filteredCategoriesKeys.map((key, i) => (
                            <div key={key} className="calc-section" >
                                <span className="calc-section-title ">
                                    <div className="d-inline-block align-middle mr-2" >
                                        <Ionicon icon={icons[i]} color="white" />
                                    </div>
                                    <span className="d-inline-blcok align-middle" >{translater[key]}</span>
                                </span>
                                <div className="">
                                    {this.state.filteredCategories[key].map((opt) => (
                                        <SlinButton to={"/CSV-Analytics/data-visualization/" + opt.key} key={opt.key} className="calc-opt" >
                                            {opt.name}
                                        </SlinButton>
                                    ))}
                                </div>
                                {
                                    i == filteredCategoriesKeys.length - 1 ?
                                    '' :
                                    <hr/>
                                }
                            </div>
                        )) :
                        <div className="calc-section" >
                            <span className="calc-section-title" >Nenhum cálculo encontrado para sua pesquisa.</span>
                        </div>
                    }
                </div>
            </div>
        );
    }
}

export default Toolbar;