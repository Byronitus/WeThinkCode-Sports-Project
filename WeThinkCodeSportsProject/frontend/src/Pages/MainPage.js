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