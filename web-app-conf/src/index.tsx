import React from 'react';
import ReactDOM from 'react-dom';
import reportWebVitals from './reportWebVitals';
//import 'bootstrap/dist/css/bootstrap.min.css';
//import './theme.scss'


const appToBuild = process.env.REACT_APP_IS_STAFF === "1" 
  ? import('./app/components/Staff/Staff')
  : import('./app/components/EndUser/EndUser');

appToBuild.then(({ default: App }) => {
  ReactDOM.render(
    <React.StrictMode>
      <App />
    </React.StrictMode>,
    document.getElementById('root')
  );
})



// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
