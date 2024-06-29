import { EDITTABLEROW, SIDEBAR, PROFILEPOPUP, PROFILEDETAIL } from './action';
const initialState = {
    someProp: 'new',
    anotherProp: 'new values',
    editableRow:null,
    sidebarOpen:false,
    profilePopup : false,
    profileDetail : []
  };
  const reducer = (state = initialState, action) => {
    switch(action.type) {
      case 'SOME_ACTION':
        return {
          ...state,
          someProp: 'new'
        };
      case 'ANOTHER_ACTION':
        return {
          ...state,
          anotherProp: 200 + action.actionPayload
        };
      case EDITTABLEROW:
        return{
          ...state,
          editableRow : action.Payload

        }
        case PROFILEDETAIL:
          return{
            ...state,
            profileDetail : action.Payload
          }
      case SIDEBAR:
        return{
          ...state,
          sidebarOpen: action.Payload
        }
      case PROFILEPOPUP:
        return{
          ...state,
          profilePopup: action.Payload
        }
      default:
        return state;
    }
  };
  export default reducer;