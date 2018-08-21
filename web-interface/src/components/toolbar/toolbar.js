import React, { Component } from 'react';
import Ionicon from 'react-ionicons';

import store from '../../store';

import SlinButton from '../util/slin-button';
import ToolbarHeader from './toolbar-header';

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
            calcOptions: store.getState().calcOptions,
            filteredCategories: store.getState().calcOptions,
            searching: false,
            scrolled: false
        };

        this.searchCalc = this.searchCalc.bind(this);
        this.toggleSearch = this.toggleSearch.bind(this);
        this.scrollContentHandler = this.scrollContentHandler.bind(this);
    }

    scrollContentHandler(e) {
        const scrollTop = e.target.scrollTop;
        if (scrollTop > 20) {
            this.setState({
                scrolled: true
            });
        } else {
            this.setState({
                scrolled: false
            });
        }
    }

    toggleSearch() {
        this.setState(prevState => {
            if (prevState.searching) {
                this.searchCalc();
            }

            return {
                ...prevState,
                searching: !prevState.searching
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
        let calcCategories = Object.keys(this.state.calcOptions);
        calcCategories.forEach(cat => {
            let selfSearchedItems = [];
            selfSearchedItems = this.state.calcOptions[cat].filter(opt => {
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
                <ToolbarHeader
                    toggleSearch={this.toggleSearch}
                    searchHandler={this.searchCalc}
                    scrolled={this.state.scrolled}
                    searching={this.state.searching}
                />
                <div 
                    onScroll={this.scrollContentHandler}
                    className="toolbar-content pt-0">
                    <hr className="m-0" />
                    {
                        filteredCategoriesKeys.length ? 
                        filteredCategoriesKeys.map((key, i) => (
                            <div key={key} className="calc-section" >
                                <span className="calc-section-title ">
                                    <div className="d-inline-block align-middle mr-2" >
                                        <Ionicon icon={icons[i]} color="white" />
                                    </div>
                                    <span className="d-inline-blcok align-middle" >{translater[key]}</span>
                                </span>
                                <div>
                                    {this.state.filteredCategories[key].map((opt) => (
                                        <SlinButton to={"/CSV-Analytics/data-visualization/" + opt.key} key={opt.key} className="calc-opt" >
                                            {opt.name}
                                        </SlinButton>
                                    ))}
                                </div>
                                {
                                    i !== filteredCategoriesKeys.length - 1 &&
                                    <hr/>
                                }
                            </div>
                        )) :
                        <div className="calc-section" >
                            <span className="calc-section-title" >
                                Nenhum cálculo encontrado para sua pesquisa.
                            </span>
                        </div>
                    }
                </div>
            </div>
        );
    }
}

export default Toolbar;