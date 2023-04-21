import React from "react"

class AddImage extends React.Component {
    imageAdd = {}
    constructor(props) {
        super(props)
        this.state = {
            title: "",
            path_of_the_samurai: "",            
        }

    }
  render() {
    return (
        <form ref={(el) => this.myForm = el}>
            <input placeholder="Название" onChange={(e) => this.setState({title: e.target.value})}/>
            <input placeholder="Путь" onChange={(e) => this.setState({path_of_the_samurai: e.target.value})}/>
            <button type="button" onClick={() => {
                    if(!this.state.title || 
                        !this.state.path_of_the_samurai) {
                        alert("Все поля должны быть заполнены")
                        return
                    }
                this.imageAdd = {
                    title: this.state.title,
                    path_of_the_samurai: this.state.path_of_the_samurai,
                    phototechId: this.props.phototech_id
                    
                }
                if(this.props.image) {
                    this.imageAdd.id = this.props.image.id
                }
                this.props.onAdd(this.imageAdd)
                this.imageAdd = {}
                this.setState({
                    title: "",
                    path_of_the_samurai: "",
                })
                this.myForm.reset()
            }}>Добавить</button>
        </form>
    )
  }
}

export default AddImage