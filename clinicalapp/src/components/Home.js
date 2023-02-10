import React from 'react';
import {Link} from 'react-router-dom';
import axios from 'axios';

class Home extends React.Component{
    state ={
        patientData:[]
    }

    handleDelete(id) {
        axios.delete('http://localhost:8080/api/patient/delete/' + id, {
          headers: {
            'Access-Control-Allow-Origin': '*'
          }
        })
          .then(response => {
            console.log(response);
            window.location.reload();
          })
          .catch(error => {
            console.error(error);
          });
      }
      

    componentWillMount(){
         axios.get('http://localhost:8080/api/patients').then(res=>{
            const patientData = res.data;
            this.setState({patientData})
        })
    }

    render(){
        return (<div>
            <h2>Patients:</h2>
            <table align='center' >
                <thead>
                    <tr>
                        <th>First Name</th>
                        <th>Last Name</th>
                        <th>Age</th>
                        <th></th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>
                    {this.state.patientData.map(patient=><RowCreator item={patient} handleDelete={this.handleDelete}  key={patient.id}/>)}
                </tbody>
            </table>
            <Link  to={'/addPatient'}>Register Patient</Link>
        </div>)
    }
}

class RowCreator extends React.Component{
    render(){
        var patient = this.props.item;
        return <tr>
            <td>{patient.firstName}</td>
            <td>{patient.lastName}</td>
            <td>{patient.age}</td>
            <td><Link to={'/patientDetails/'+patient.id}>Add Clinical Data</Link></td>
            <td><Link to={'/analyze/'+patient.id}>Analyze</Link></td>
            <td><button className='trash-button' onClick={() => this.props.handleDelete(patient.id)}>Delete</button></td>
        </tr>
    }
}

export default Home;