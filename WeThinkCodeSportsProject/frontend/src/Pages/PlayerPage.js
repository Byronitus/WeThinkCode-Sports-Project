import React, {Component} from "react";
import axios from "axios";
import Header from "../components/layouts/Header";
import MultipleLines from "../components/Map/MultipleLines";

export class PlayerPage extends Component {
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
        let text = "Name : "+this.props.location.state.PlayerName+
            "\nDescription : "+this.props.location.state.PlayerDescription+
            "\nHeight : "+this.props.location.state.Height+
            "\nWeight : "+this.props.location.state.Weight+
            "\nGender : "+this.props.location.state.Gender +
            "\nBirth Location : "+this.props.location.state.BirthLocation +
            "\nDate of birth : "+this.props.location.state.DOB +
            "\nNationality : "+this.props.location.state.Nationality;
        return (
            <div className="container">
                <Header/>
                <div>
                    <MultipleLines text={text}/>
                </div>
            </div>

        );
    }
}


export default PlayerPage;