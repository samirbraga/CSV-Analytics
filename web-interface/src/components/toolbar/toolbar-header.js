import React, { Component } from 'react';
import cx from 'classnames';

import RoundButton from '../util/round-button';

import './toolbar.css';

class ToolbarHeader extends Component {
    render() {
        return (
            <div className={cx(
                "toolbar-header",
                "pb-3 pt-3 px-3 clearfix",
                {
                    "shadow": this.props.scrolled
                }
            )}>
                <div
                    className={cx(
                        "search-container",
                        "float-left",
                        {
                            "d-none": !this.props.searching
                        }
                    )}
                >
                    <input type="text" onKeyUp={e => this.props.searchHandler(e.target.value)} />
                </div>
                <div
                    className={cx(
                        "toolbar-header-title",
                        "float-left",
                        "mt-1",
                        {
                            "d-none": this.props.searching
                        }
                    )} >
                    <img className="d-inline-block align-middle mr-3" src="/CSV-Analytics/imgs/white-icon.png" alt="Logo - CSV-Analytics" />
                    <span className="d-inline-block align-middle" ><strong>CSV</strong> ANALYTICS</span>
                </div>
                <RoundButton
                    onClick={this.props.toggleSearch}
                    actived={this.props.searching}
                    icon="ios-search"
                    className="float-right"
                />
            </div>
        );
    }
}

export default ToolbarHeader;