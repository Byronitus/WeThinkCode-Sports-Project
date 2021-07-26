import React, {Component} from "react";
import {Redirect} from "react-router-dom";
import Button from "@material-ui/core/Button";
import PropTypes from "prop-types";

class ListOfPlayersInfo extends Component{

    state ={check : false};

    setCheck = () => {
        this.setState({check:true});
    }

    infoStyle = () =>{
        return {
            backgroundColor: (this.props.user.id %2) == 0? '#c8d4f7cc' :'',
            padding:'10px',
            borderBottom: '1px #ccc dotted',
            display:'flex',
            alignItems:'center',
            justifyContent:'space-between',
            justifyItems: 'flex-start',
        }
    }

    render() {
        if (this.state.check){
            return <Redirect push to={{pathname: "/Player",state: {PlayerName : this.props.user.strPlayer, PlayerDescription : this.props.user.strDescriptionEN,
            Height : this.props.user.strHeight, Weight : this.props.user.strWeight, Gender : this.props.user.strGender,
                BirthLocation : this.props.user.strBirthLocation, DOB : this.props.user.dateBorn, Nationality : this.props.user.strNationality,
                }}}/>
        }
        const {id} = this.props.user;
        return(
            <div style={this.infoStyle()}>
                <Button color="primary" variant="outlined" onClick={this.setCheck}>
                    {this.props.user.strPlayer}
                </Button>
                <div className="shoe-container" style={{ justifyContent:'center', alignItems:'center'}}>
                    <img src={this.props.user.strThumb} alt=""/>
                </div>
            </div>
        );
    }
}

ListOfPlayersInfo.propTypes = {
    user : PropTypes.object.isRequired
}
export  default  ListOfPlayersInfo;