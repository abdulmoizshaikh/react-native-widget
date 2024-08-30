import { StyleSheet } from "react-native";

const styles = StyleSheet.create({
    container: {
      flex: 1,
      backgroundColor: '#F8F8F8',
      paddingHorizontal: 20,
      paddingVertical: 40,
    },
    title: {
      fontSize: 24,
      fontWeight: 'bold',
      color: '#333',
      textAlign: 'center',
      marginBottom: 20,
    },
    input: {
      backgroundColor: '#fff',
      borderRadius: 8,
      padding: 15,
      fontSize: 16,
      marginBottom: 10,
      borderColor: '#ddd',
      borderWidth: 1,
    },
    addButton: {
      backgroundColor: '#4CAF50',
      paddingVertical: 15,
      borderRadius: 8,
      marginBottom: 20,
    },
    addButtonText: {
      color: '#fff',
      fontSize: 16,
      textAlign: 'center',
      fontWeight: 'bold',
    },
    todoItem: {
      flexDirection: 'row',
      justifyContent: 'space-between',
      paddingVertical: 15,
      paddingHorizontal: 10,
      backgroundColor: '#fff',
      borderRadius: 8,
      marginBottom: 10,
      borderColor: '#ddd',
      borderWidth: 1,
    },
    todoText: {
      fontSize: 16,
      color: '#333',
    },
    completed: {
      textDecorationLine: 'line-through',
      color: '#999',
    },
    deleteText: {
      color: '#E74C3C',
      fontWeight: 'bold',
    },
  });
  
  export default styles;