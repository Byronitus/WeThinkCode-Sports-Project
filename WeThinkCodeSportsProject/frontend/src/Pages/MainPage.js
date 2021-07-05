import React, {Component, useCallback} from "react";
import ReactDOM from "react-dom";
import Header from '../components/layouts/Header';
// import RegisterUser from '../../../frontend/src/components/register-user/RegisterUser';
import Sports from '../components/Map/Sports';
import axios from "axios";

export class MainPage extends Component {
    constructor(props) {
        super(props);
        this.state = {
            sports:[
            ]
        }
    }

    componentDidMount() {
        axios.get('/api/sport')
            .then(response => this.setState({sports:response.data}))
    }

    // //Deleting User
    // removeUser = (id) =>{
    //     axios.delete(`/user/${id}`)
    //         .then(
    //             response =>this.setState( //Updating UI
    //                 {users: [...this.state.users.filter(
    //                         user => user.id !== id
    //                     )]}
    //             )
    //         );
    // }
    //
    // addUser = (newUser) =>{
    //     axios.post('/user/save',newUser)
    //         .then(
    //             (response) =>{
    //                 console.log(response.data);
    //                 this.setState({users:[...this.state.users,response.data]})
    //             }
    //         );
    // }

    render() {
        return (
            <div className="container">
                <Header/>
                {/*<RegisterUser addUser={this.addUser}/>*/}
                <Sports sports={this.state.sports}/>
            </div>

        );
    }
}


export default MainPage;

// ReactDOM.render(<App />, document.querySelector("#app"));