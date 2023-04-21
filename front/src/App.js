import React from "react"
import { BrowserRouter as Router, Route, Switch, BrowserRouter, Routes } from "react-router-dom"
import PhototechApp from "./PhototechApp"
import ImageApp from "./ImageApp"

class App extends React.Component {
  render() {
    return (
      <BrowserRouter>
        <Routes>
          <Route path="/" element={<PhototechApp />} />
          <Route path="/:id" element={<ImageApp />} />
        </Routes>
      </BrowserRouter>
    )
  }
}

export default App