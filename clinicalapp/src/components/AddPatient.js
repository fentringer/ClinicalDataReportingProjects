import React from 'react';
import axios from 'axios';
import {Link} from 'react-router-dom';
import '../App.css';


class AddPatient extends React.Component{
 
    handleSubmit(event){
        event.preventDefault();
        const data = {
            firstName:this.firstName,
            lastName:this.lastName,
            age:this.age
        }
        console.log(data);
        axios.post("http://localhost:8080/api/patients",data)
        .then(res=>{
          //  document.write("Patient Created Successfully!!");
        })
        window.location.replace("/");
    }

    render(){
        return (<div >
                <h2>Create Patient:</h2>
                <form className='form-input'>
                First Name:<input type="text" name="firstName" onChange={(event)=>{this.firstName=event.target.value}} align="left"/>
                Last Name:<input type="text" name="lastName" onChange={(event)=>{this.lastName=event.target.value}} align="left"/>
                Age:<input type="text" name="age" onChange={(event)=>{this.age=event.target.value}} align="left"/>
                <button onClick={this.handleSubmit.bind(this)} >Confirm</button>
                </form>
                <Link  to={'/'}>Go Back</Link>
        </div>)
    }
}

export default AddPatient;






