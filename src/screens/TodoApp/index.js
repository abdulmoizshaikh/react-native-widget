import React, {useState, useEffect} from 'react';
import {
  SafeAreaView,
  View,
  TextInput,
  FlatList,
  TouchableOpacity,
  Text,
  Alert,
} from 'react-native';
import SharedPreferences from 'react-native-shared-preferences';
import styles from './styles';

const TodoApp = () => {
  const [todos, setTodos] = useState([]);
  const [inputText, setInputText] = useState('');

  useEffect(() => {
    loadTodosFromPreferences();
  }, []);

  const saveTodos = newTodos => {
    try {
      SharedPreferences.setItem('todos', JSON.stringify(newTodos));
      setTodos(newTodos);
    } catch (e) {
      console.error('Failed to save todos.', e);
    }
  };

  const loadTodosFromPreferences = () => {
    SharedPreferences.getItem('todos', value => {
      if (value) {
        const _todos = JSON.parse(value);
        setTodos(_todos);
      }
    });
  };

  const addTodo = () => {
    if (inputText.trim()) {
      const newTodos = [
        ...todos,
        {id: Date.now().toString(), text: inputText, completed: false},
      ];
      saveTodos(newTodos);
      setInputText('');
    } else {
      Alert.alert('Error', 'Please enter a todo item.');
    }
  };

  const toggleTodo = id => {
    const newTodos = todos.map(todo =>
      todo.id === id ? {...todo, completed: !todo.completed} : todo,
    );
    saveTodos(newTodos);
  };

  const deleteTodo = id => {
    const newTodos = todos.filter(todo => todo.id !== id);
    saveTodos(newTodos);
  };

  return (
    <SafeAreaView style={styles.container}>
      <Text style={styles.title}>Todo List</Text>
      <TextInput
        style={styles.input}
        placeholder="Add a new task..."
        value={inputText}
        onChangeText={setInputText}
      />
      <TouchableOpacity style={styles.addButton} onPress={addTodo}>
        <Text style={styles.addButtonText}>Add</Text>
      </TouchableOpacity>
      <FlatList
        data={todos}
        keyExtractor={item => item.id}
        renderItem={({item}) => (
          <View style={styles.todoItem}>
            <TouchableOpacity onPress={() => toggleTodo(item.id)}>
              <Text
                style={[styles.todoText, item.completed && styles.completed]}>
                {item.text}
              </Text>
            </TouchableOpacity>
            <TouchableOpacity onPress={() => deleteTodo(item.id)}>
              <Text style={styles.deleteText}>X</Text>
            </TouchableOpacity>
          </View>
        )}
      />
    </SafeAreaView>
  );
};

export default TodoApp;
