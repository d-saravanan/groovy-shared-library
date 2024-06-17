def call(Map config = [:]) {
  echo "hello ${config.user} - ${config.greeting}"
}
