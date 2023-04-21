import { useParams } from "react-router-dom";

function usePhototechParams() {
  const { id } = useParams();
  return { id };
}