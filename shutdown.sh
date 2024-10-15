#kill $(cat ./pid.file)
PID=$(cat ./pid.file)

if [ -n "$PID" ]; then
    if kill -0 "$PID" 2>/dev/null; then
        echo "Killing process $PID"
        kill "$PID" || echo "Failed to kill process $PID"
    else
        echo "Process with PID $PID is not running"
    fi
else
    echo "No process ID found in pid.file"
fi