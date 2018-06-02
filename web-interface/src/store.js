import { createStore } from 'redux';

let initialState = {
    "api_host": "https://csv-analytics.herokuapp.com",
    "current_token": "",
    "csv_data": null,
    "calcOptions": {
        "metrics": [
            {
                key: 'average',
                name: 'MÉDIA',
                type: 'metrics'
            },
            {
                key: 'median',
                name: 'MEDIANA',
                type: 'metrics'
            },
            {
                key: 'kurtosis',
                name: 'KURTOSIS',
                type: 'metrics'
            },
            {
                key: 'variance',
                name: 'VARIÂNCIA',
                type: 'metrics'
            },
            {
                key: 'standart-deviation',
                name: 'DESVIO PADRÃO',
                type: 'metrics'
            },
            {
                key: 'min',
                name: 'MÍNIMO',
                type: 'metrics'
            },
            {
                key: 'max',
                name: 'MÁXIMO',
                type: 'metrics'
            },
            {
                key: 'skewness',
                name: 'SKEWNESS',
                type: 'metrics'
            },
            {
                key: 'covariance',
                name: 'COVARIÂNCIA',
                type: 'metrics'
            },
            {
                key: 'mode',
                name: 'MODA',
                type: 'metrics'
            },
            {
                key: 'correlation-coefficient',
                name: 'COEFICIENTE DE CORRELAÇÃO',
                type: 'metrics'
            }
        ],
        "graphic-qualitatives": [
            {
                key: 'qualitative-frequency-table',
                name: 'TABELA DE FREQUÊNCIA',
                type: 'graphic-qualitatives'
            },
            {
                key: 'graphic-bar',
                name: 'GRÁFICO DE BARRA',
                type: 'graphic-qualitatives'
            },
            {
                key: 'contingency-table',
                name: 'TABELA DE CONTINGÊNCIA',
                type: 'graphic-qualitatives'
            }
        ],
        "graphic-quantitatives": [
            {
                key: 'histogram',
                name: 'HISTOGRAMA',
                type: 'graphic-quantitatives'
            },
            {
                key: 'boxplot',
                name: 'BOXPLOT',
                type: 'graphic-quantitatives'
            },
            {
                key: 'scatterplot',
                name: 'SCATTERPLOT',
                type: 'graphic-quantitatives'
            },
            {
                key: 'quantitative-frequency-table',
                name: 'TABELA DE FREQUÊNCIA',
                type: 'graphic-quantitatives'
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

