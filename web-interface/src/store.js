import { createStore } from 'redux';

let initialState = {
    "api_host": "http://localhost:8080",
    "current_token": "",
    "csv_data": null,
    "calcOptions": {
        "metrics": [
            {
                key: 'average',
                name: 'MÉDIA'
            },
            {
                key: 'median',
                name: 'MEDIANA'
            },
            {
                key: 'kurtosis',
                name: 'KURTOSIS'
            },
            {
                key: 'variance',
                name: 'VARIÂNCIA'
            },
            {
                key: 'standart-deviation',
                name: 'DESVIO PADRÃO'
            },
            {
                key: 'min',
                name: 'MÍNIMO'
            },
            {
                key: 'max',
                name: 'MÁXIMO'
            },
            {
                key: 'skewness',
                name: 'SKEWNESS'
            },
            {
                key: 'covariance',
                name: 'COVARIÂNCIA'
            },
            {
                key: 'mode',
                name: 'MODA'
            },
            {
                key: 'correlation-coefficient',
                name: 'COEFICIENTE DE CORRELAÇÃO'
            }
        ],
        "graphic-qualitatives": [
            {
                key: 'frequency-table',
                name: 'TABELA DE FREQUÊNCIA'
            },
            {
                key: 'graphic-bar',
                name: 'GRÁFICO DE BARRA'
            },
            {
                key: 'contingency-table',
                name: 'TABELA DE CONTINGÊNCIA'
            }
        ],
        "graphic-quantitatives": [
            {
                key: 'histogram',
                name: 'HISTOGRAMA'
            },
            {
                key: 'boxplot',
                name: 'BOXPLOT'
            },
            {
                key: 'scatterplot',
                name: 'SCATTERPLOT'
            },
            {
                key: 'contingency-table',
                name: 'TABELA DE CONTINGÊNCIA'
            }
        ]
    }
};


const reducer = (state = initialState, action) => {
    switch (action.type) {
        case 'SET_TOKEN':
            state = {
                ...state,
                "current_token": action.payload
            };
            break;
        case 'SET_CSV_DATA':
            state = {
                ...state,
                "csv_data": action.payload
            };
            break;
        default:
            return state;
    }

    return state;
};

const store = createStore(reducer);

export default store;

