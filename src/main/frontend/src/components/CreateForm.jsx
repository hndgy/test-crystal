import React from 'react'

function CreateForm({handleCreate}) {
  return (
    <form name="createForm" onSubmit={handleCreate}>
    <label>
      Longitude :
    <input name="longitude"/>
    </label>
    <label>
      Latitude :
    <input name="latitude" />
    </label>
    <button type="submit">Créer</button>
  </form>
  )
}

export default CreateForm