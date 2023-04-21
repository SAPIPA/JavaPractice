import React from "react"
import AddPhototech from "./AddPhototech"
import {IoCloseCircleSharp, IoHammerSharp, IoList} from 'react-icons/io5'
import { BrowserRouter as Router, Route, Switch, BrowserRouter, Routes, NavLink } from "react-router-dom"


class Phototech extends React.Component {
    constructor(props) {
        super(props)
        this.state = {
            editForm: false
        }
    }
    phototech = this.props.phototech
    render() {
        return (
            <div className="phototechs">
                <IoCloseCircleSharp onClick={() => this.props.onDelete(this.phototech.id)} className="delete-icon"/>
                <IoHammerSharp onClick={() => {
                    this.setState({
                        editForm: !this.state.editForm
                    })
                }} className="edit-icon" />
                <h3>{this.phototech.serial_number}</h3>
                <h3>{this.phototech.matrix_resolution}</h3>
                <h3>{this.phototech.viewfinder_type}</h3>
                <h3>{this.phototech.optical_zoom}</h3>
                <h3>{this.phototech.menu_language}</h3>
                <h3>{this.phototech.flash_range}</h3>
                
                {this.state.editForm && <AddPhototech phototech={this.phototech} onAdd={this.props.onEdit} />}
                <NavLink to={`/${this.phototech.id}`}>Список фотографий</NavLink>
            </div>
        )
    }
}
export default Phototech 