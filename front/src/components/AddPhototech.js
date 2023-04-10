import React from "react"

class AddPhototech extends React.Component {
    phototechAdd = {}
    constructor(props) {
        super(props)
        this.state = {
            serial_number: "",
            matrix_resolution: "",
            viewfinder_type: "",
            optical_zoom: "",
            menu_language: "",
            flash_range: ""
        }
    }
  render() {
    return (
        <form ref={(el) => this.myForm = el}>
            <input placeholder="Серийный номер" onChange={(e) => this.setState({serial_number: e.target.value})}/>
            <input placeholder="Разрешение матрицы" onChange={(e) => this.setState({matrix_resolution: e.target.value})}/>
            <input placeholder="Тип видеоискателя" onChange={(e) => this.setState({viewfinder_type: e.target.value})}/>
            <input placeholder="Оптический зум" onChange={(e) => this.setState({optical_zoom: e.target.value})}/>
            <input placeholder="Язык меню" onChange={(e) => this.setState({menu_language: e.target.value})}/>
            <input placeholder="Дальность вспышки" onChange={(e) => this.setState({flash_range: e.target.value})}/>
            <button type="button" onClick={() => {
                this.myForm.reset()
                this.phototechAdd = {
                    serial_number: this.state.serial_number,
                    matrix_resolution: this.state.matrix_resolution,
                    viewfinder_type: this.state.viewfinder_type,
                    optical_zoom: this.state.optical_zoom,
                    menu_language: this.state.menu_language,
                    flash_range: this.state.flash_range,
                }
                if(this.props.phototech)
                    this.phototechAdd.id = this.props.phototech.id
                this.props.onAdd(this.phototechAdd)}
            }>Добавить</button>
        </form>
    )
  }
}

export default AddPhototech