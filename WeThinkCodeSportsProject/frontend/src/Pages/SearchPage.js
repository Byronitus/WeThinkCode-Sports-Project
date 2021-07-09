import React, {Component} from "react";
import axios from "axios";
import Header from "../components/layouts/Header";
import Sports from "../components/Map/Sports";
import Search from "../components/Map/Search";

export class SearchPage extends Component {
    constructor(props) {
        super(props);
        this.state = {
            search:[
            ]
        }
    }

    componentDidMount() {
        axios.get('/api/Search/' + this.props.location.state.SearchValue)
            .then(response => this.setState({search:response.data}))
    }


    render() {
        return (
            <div className="container">
                <Header/>
                {/*{this.props.location.state.SearchValue}*/}
                <Search SearchList={this.state.search}/>
            </div>

        );
    }
}


export default SearchPage;