import { FunctionComponent } from "react";
import { App } from "../App";
import UpdateConf from "./Conferences/UpdateConf";

const StaffApp: FunctionComponent = () => {
  return <App>
  <UpdateConf confId='1'/>
  </App>
}

export default StaffApp