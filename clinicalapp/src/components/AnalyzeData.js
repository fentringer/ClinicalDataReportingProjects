import React from 'react';
import axios from 'axios';
import {Link} from 'react-router-dom';



class AnalyzeData extends React.Component{
    state ={
        clinicalData:[]
    }

   
    componentWillMount(){

         axios.get('http://localhost:8080/api/patients/' + this.props.match.params.patientId + '/analyze'
        ).then(res=>{
             console.log(res.data)
            this.setState(res.data)
            console.log(this.state)
        })
    }

    render(){
        return (<div>
              <h2>Patient Details:</h2>
                First Name: {this.state.firstName}<br/>
                Last Name: {this.state.lastName}<br/>
                Age: {this.state.age}
              
            <h2>Clinical Report:</h2>
                    {this.state.clinicalData.map(eachEntry=><RowCreator item={eachEntry} 
                    patientId={this.state.id}/>)}
        </div>)
    }
}

class RowCreator extends React.Component{
    render(){
        var eachEntry = this.props.item;
        var patientId = this.props.patientId;
       
        return<div>
            <table border="1">
            <tr>
                <td><b>{eachEntry.componentName}</b></td>
            </tr>
            <tr>
            <td>{eachEntry.componentName}</td>
            <td>{eachEntry.componentValue}</td>
            <td>{eachEntry.measuredDateTime}</td>
            <td><Link to={'/chart/'+eachEntry.componentName+'/'+patientId}>
            <img alt='' src={require('../Logo.png')} height='20' widtch='20'/></Link>
            </td>
        </tr>
        </table>
        </div>
    }
}



export default AnalyzeData;