TYPES = {
  'integer' => 'long',
  'array of strings' => 'com.google.common.collect.ImmutableList<String>'  
}

def get_java_type(t)
  TYPES[t]
end
