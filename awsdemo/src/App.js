import React from 'react';
import {Switch,Route} from 'react-router-dom';
import './App.css';
import NavBar from './components/NavBar';
import FormCom from './components/FormCom';
import HomeComp from './components/HomeComp';


function App() {
  return (
   <React.Fragment>
   <NavBar/>
   <Switch>
        <Route exact path="/home" component={HomeComp}></Route>
       <Route path="/form" component={FormCom}></Route>
      
      
     </Switch>


   </React.Fragment>
  );
}

export default App;
