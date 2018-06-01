import React, { Component } from 'react';
import { Row, Col } from 'reactstrap';
import { withRouter } from 'react-router';
import { Redirect } from 'react-router-dom';

import store from '../../store';

import './drop-area.css';

class DropArea extends Component {
    constructor(props) {
        super(props);

        this.dropArea = React.createRef();
        this.inputFile = React.createRef();

        this.state = {
            dragging: false,
            selectedFile: null
        };

        this.dragStart             = this.dragStart.bind(this);
        this.dragEnd               = this.dragEnd.bind(this);
        this.showChooserFileWindow = this.showChooserFileWindow.bind(this);
        this.getDroppedFile        = this.getDroppedFile.bind(this);
        this.chooseFile            = this.chooseFile.bind(this);
        this.sendFile              = this.sendFile.bind(this);
    }

    dragStart(e) {
        e.stopPropagation();
        e.preventDefault();
        this.dropArea.current.classList.toggle('dragging');
    }

    dragEnd(e) {
        e.stopPropagation();
        e.preventDefault();
        this.dropArea.current.classList.toggle('dragging');
    }

    showChooserFileWindow() {
        this.inputFile.current.click();
    }

    getDroppedFile(e) {
        e.stopPropagation();
        e.preventDefault();

        this.dropArea.current.classList.toggle('dragging');

        let file = e.dataTransfer.files[0];
        this.setState({
            selectedFile: file
        }, () => {
            this.sendFile();
        });
    }

    chooseFile() {
        let file = this.inputFile.current.files[0];
        this.setState({
            selectedFile: file
        }, () => {
            this.sendFile();
        });
    }

    sendFile() {
        let file = this.state.selectedFile;

        let host = store.getState().api_host;

        let data = new FormData();
        data.append('file', file);

        fetch(`${host}/api/upload-csv`, {
            method: 'POST',
            body: data
        }).then(res => {
            res.json().then(json => {
                let csv_data = {
                    header: json.header,
                    records: json.records
                };

                store.dispatch({
                    type: 'SET_CSV_DATA',
                    payload: csv_data
                });
                store.dispatch({
                    type: 'SET_TOKEN',
                    payload: json.token[0]
                });

                localStorage.setItem('csv_data', JSON.stringify(csv_data));
                localStorage.setItem('token', JSON.stringify({ token: json.token[0] }));

                this.props.history.push('/CSV-Analytics/data-visualization');
            }).catch(e => console.log(e));
        });
    }

    render() {
        return (
            <div ref={this.dropArea} 
                onDragEnter={this.dragStart} 
                onDragLeave={this.dragEnd}
                onDragOver={(e) => e.preventDefault()}
                onDrop={this.getDroppedFile}
                className="drop-area w-100 h-100">
                <input type="file" onChange={this.chooseFile} ref={this.inputFile} name="csv-file" id="csv-file" hidden />
                <div
                    className="overlay-darker"></div>
                <div
                    onClick={this.showChooserFileWindow} 
                    className="overlay-line"></div>
            </div>
        );
    }
}

export default withRouter(DropArea);