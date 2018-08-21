import ReactDOM from 'react-dom';
import 'bootstrap/dist/css/bootstrap.min.css';
import './index.css';
import Router from './router';
import registerServiceWorker from './registerServiceWorker';

ReactDOM.render(Router, document.getElementById('root'));
registerServiceWorker();
