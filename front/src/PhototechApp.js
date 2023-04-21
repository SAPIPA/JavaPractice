import React from "react"
import Header from "./components/Header"
import Phototechs from "./components/Phototechs"
import AddPhototech from "./components/AddPhototech"
import axios from "axios"

const baseUrl = "/phototech"

class PhototechApp extends React.Component {
  constructor(props) {
    super(props)

    this.state = {
        phototechs: []
    }
    
    this.getPhototechs = this.getPhototechs.bind(this)
    this.addPhototech = this.addPhototech.bind(this)
    this.deletePhototech = this.deletePhototech.bind(this)
    this.editPhototech = this.editPhototech.bind(this)

    this.getPhototechs()
}

  render() {
    return (<div>
        <Header title="Список фототехники" />
        <main>
          <Phototechs phototechs={this.state.phototechs} onEdit={this.editPhototech} onDelete={this.deletePhototech}/>
        </main>
        <aside>
          <AddPhototech onAdd={this.addPhototech}/>
        </aside>
      </div>)
  }

  getPhototechs() {
      axios.get(baseUrl).then((res) => {
      this.setState({phototechs: []}, ()=> {this.setState({phototechs: res.data})})
      console.log(res.data)
    })
  }

  deletePhototech(id) {
    axios.delete(baseUrl + "/" + id).then((res) => {
    this.getPhototechs()
    console.log("Удаление\n" + res)
    })
  }

  editPhototech(phototech) {
      axios.put(baseUrl + "/" + phototech.id, phototech).then(res => {
      this.getPhototechs()  
      console.log("Обновление\n" + res)
    })
  }
  
  addPhototech(phototech) {
      axios.post(baseUrl, phototech).then((res) => {
      console.log("Добавлени\n" + res)
      this.getPhototechs()
    })
  }
}

export default PhototechApp