import React, {Component} from "react";
import {Redirect} from "react-router-dom";
import Button from "@material-ui/core/Button";
import PropTypes from "prop-types";

class SearchInfo extends Component{

    state ={check1 : false, check2  : false, check3 : false};

    setCheck1 = () => {
        this.setState({check1:true});
    }

    setCheck2 = () => {
        this.setState({check2:true});
    }

    setCheck3 = () => {
        this.setState({check3:true});
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

        if (this.state.check1){
            return <Redirect push to={{pathname: "/Player",state: {PlayerName : this.props.user.strPlayer, PlayerDescription : this.props.user.strDescriptionEN,
                    Height : this.props.user.strHeight, Weight : this.props.user.strWeight, Gender : this.props.user.strGender,
                    BirthLocation : this.props.user.strBirthLocation, DOB : this.props.user.dateBorn, Nationality : this.props.user.strNationality,
                }}}/>
        }

        if (this.state.check2){
            return <Redirect push to={{pathname: "/team",state: {TeamID : this.props.user.idTeam, TeamDescription : this.props.user.strDescriptionEN}}}/>
        }

        if (this.state.check3){
            return <Redirect push to={{pathname: "/Events",state: {Description : this.props.user.strDescriptionEN, Video : this.props.user.strVideo ,sportType : this.props.user.strSport,
                    Event : this.props.user.strFilename, League : this.props.user.strLeague, Round : this.props.user.intRound,
                    Date : this.props.user.dateEvent, Venue : this.props.user.strVenue, Country : this.props.user.strCountry, Started : this.props.user.strStatus}}}/>
        }

        const {id} = this.props.user;
        console.log(this.props.user.strTeamLogo)
        if (this.props.user.strTeamLogo){
            return(
                <div style={this.infoStyle()}>
                    <Button color="primary" variant="outlined" onClick={this.setCheck2}>
                        {this.props.user.strTeam}
                    </Button>
                    <div className="shoe-container" style={{ justifyContent:'center', alignItems:'center'}}>
                        <img src={this.props.user.strTeamLogo} alt=""/>
                    </div>
                </div>
            );
        }else if (this.props.user.strPlayerImage){
            return(
                <div style={this.infoStyle()}>
                    <Button color="primary" variant="outlined" onClick={this.setCheck1}>
                        {this.props.user.strPlayer}
                    </Button>
                    <div className="shoe-container" style={{ justifyContent:'center', alignItems:'center'}}>
                        <img src={this.props.user.strPlayerImage} alt=""/>
                    </div>
                </div>
            );
        }else{
            return(
                <div style={this.infoStyle()}>
                    <Button color="primary" variant="outlined" onClick={this.setCheck3}>
                        {this.props.user.strEvent}
                    </Button>
                    <div className="shoe-container" style={{ justifyContent:'center', alignItems:'center'}}>
                        <img src={this.props.user.strThumb} alt=""/>
                    </div>
                </div>
            );
        }
    }
}


SearchInfo.propTypes = {
    user : PropTypes.object.isRequired
}
export  default  SearchInfo;